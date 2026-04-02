const amqp = require('amqplib');
const RABBITMQ_URL = process.env.RABBITMQ_URL || 'amqp://rabbitmq';

let connection = null;
let channel = null;

async function connectRabbitMQ() {
  try {
    connection = await amqp.connect(RABBITMQ_URL);
    channel = await connection.createChannel();
    
    console.log("✅ [RabbitMQ] Connecté et canal créé");

    connection.on('error', (err) => {
      console.error("❌ [RabbitMQ] Erreur de connexion, reconnexion...");
      setTimeout(connectRabbitMQ, 5000);
    });

    return { connection, channel };
  } catch (error) {
    console.error("⏳ [RabbitMQ] Pas prêt. Nouvelle tentative dans 5s...");
    return new Promise((resolve) => {
      setTimeout(() => resolve(connectRabbitMQ()), 5000);
    });
  }
}


async function publierCommande(donnees) {
  if (!channel) {
    console.error("❌ Impossible de publier : Canal RabbitMQ non initialisé");
    return;
  }
  const queue = 'commandes_queue';
  await channel.assertQueue(queue, { durable: true });
  channel.sendToQueue(queue, Buffer.from(JSON.stringify(donnees)), {
    persistent: true
  });
  console.log("📨 [RabbitMQ] Commande publiée :", donnees.id);
}

// MISE À JOUR DE L'EXPORT : Ajoute publierCommande ici
module.exports = { connectRabbitMQ, getChannel: () => channel, publierCommande };