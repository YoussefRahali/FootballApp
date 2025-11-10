const { Staff } = require('../models/staff.model');
const { getClubById } = require('./club.client');

async function createStaff(payload) {
    const club = await getClubById(payload.equipeId);
    if (!club) throw new Error('Le club spécifié est introuvable');
    return Staff.create(payload);
}

async function updateStaff(id, payload) {
    const s = await Staff.findByPk(id);
    if (!s) throw new Error('Membre non trouvé');
    if (payload.equipeId) {
        const club = await getClubById(payload.equipeId);
        if (!club) throw new Error('Le club spécifié est introuvable');
    }
    await s.update(payload);
    return s;
}

async function listStaff(query = {}) { return Staff.findAll({ where: query }); }
async function getStaff(id) { const s=await Staff.findByPk(id); if(!s) throw new Error('Membre non trouvé'); return s; }
async function removeStaff(id) { const s=await getStaff(id); await s.destroy(); return {deleted:true}; }

module.exports = { createStaff, updateStaff, listStaff, getStaff, removeStaff };
