// app.js
const express = require('express');
const cors = require('cors');
require('dotenv').config();

const app = express();

const { Eureka } = require('eureka-js-client');

// middlewares
app.use(cors());
app.use(express.json());

// routes
app.get('/health', (_, res) => res.json({ ok: true }));
app.use('/staff', require('./routes/staff.routes'));
app.use(require('./middlewares/error'));

const port = process.env.PORT || 8085;
app.listen(port, () => console.log(`microStaff en écoute sur ${port}`));

// DB sync
const { sequelize } = require('./models');
(async () => {
  await sequelize.sync({ alter: true });
  console.log('Base de données synchronisée');
})();

const eurekaClient = new Eureka({
  instance: {
    app: process.env.EUREKA_APP_NAME || 'microStaff',
    hostName: process.env.EUREKA_INSTANCE_HOSTNAME || 'Staffmicro',
    ipAddr: process.env.EUREKA_INSTANCE_IP || 'Staffmicro',
    port: { '$': Number(process.env.PORT || 8891), '@enabled': true },
    vipAddress: process.env.EUREKA_APP_NAME || 'microStaff',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn'
    }
  },
  eureka: {
    host: process.env.EUREKA_HOST || 'localhost',
    port: Number(process.env.EUREKA_PORT || 8761),
    servicePath: '/eureka/apps/'
  }
});

eurekaClient.start((err) => {
  if (err) console.error('Eureka registration failed:', err.message);
  else console.log('✅ microStaff enregistré dans Eureka');
});

module.exports = eurekaClient;