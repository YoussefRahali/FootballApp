const { DataTypes, Model } = require('sequelize');
const { sequelize } = require('./index');

const ROLES = [
    'ASSISTANT_ENTRENEUR',
    'MEDECIN',
    'PREPARATEUR_PHYSIQUE',
    'PREPARATEUR_MENTAL'
];

class Staff extends Model {}

Staff.init({
    id: { type: DataTypes.INTEGER, autoIncrement: true, primaryKey: true },
    fullName: { type: DataTypes.STRING, allowNull: false },
    role: { type: DataTypes.ENUM(...ROLES), allowNull: false },
    equipeId: { type: DataTypes.STRING, allowNull: false },
    phone: { type: DataTypes.STRING },
    email: { type: DataTypes.STRING, unique: true },
    active: { type: DataTypes.BOOLEAN, defaultValue: true }
}, { sequelize, tableName: 'staffs', modelName: 'staff' });

module.exports = { Staff };
