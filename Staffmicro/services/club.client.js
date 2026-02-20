const axios = require('axios');
require('dotenv').config();

const GATEWAY = process.env.CLUB_SERVICE_URL || 'http://gateway:8222';

// Adapter le chemin selon ton contrôleur microClub
// Ici on suppose GET /clubs/{id}
async function getClubById(id) {
    try {
        const { data } = await axios.get(`${GATEWAY}/clubs/${id}`, { timeout: 5000 });
        return data; // 200 -> club trouvé
    } catch (err) {
        if (err.response && err.response.status === 404) return null; // club inexistant
        throw new Error('Erreur de communication avec microClub via Gateway');
    }
}

module.exports = { getClubById };
