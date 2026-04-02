// app/server.js
const express = require('express');
// Utilise des chemins relatifs corrects (vérifie si c'est ./services ou ../services)
const { connectMongo } = require('./services/mongodb'); 
const { connectRabbitMQ } = require('./services/rabbitmq');
const commandesRoutes = require('./routes/commandes');

const app = express();
const PORT = process.env.PORT || 3001;
const INSTANCE = process.env.INSTANCE || 'Instance-?';

app.use(express.json());

// Routes
app.use('/commandes', commandesRoutes);

// Route santé (Indispensable pour Nginx)
app.get('/health', (req, res) => {
  res.json({ 
    status: 'OK', 
    instance: INSTANCE,
    uptime: process.uptime() 
  });
});

// Démarrage avec gestion d'erreurs
async function start() {
  // 1. On lance le serveur immédiatement pour que Nginx et Docker voient l'app
  const server = app.listen(PORT, '0.0.0.0', () => {
    console.log(`🚀 ${INSTANCE} lancée sur le port ${PORT}`);
  });

  try {
    console.log(`[${INSTANCE}] Connexion aux services...`);
    
    // 2. On tente les connexions en arrière-plan
    await connectMongo();
    console.log("✅ MongoDB connecté");
    
    await connectRabbitMQ();
    console.log("✅ RabbitMQ connecté");

  } catch (error) {
    console.error(`❌ Erreur de connexion aux services:`, error.message);
    // On ne coupe pas le serveur, on laisse les services retenter leur chance
  }
}

start();

