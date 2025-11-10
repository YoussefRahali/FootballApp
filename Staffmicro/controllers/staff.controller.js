const service = require('../services/staff.service');

exports.create = async (req, res, next) => { try { res.status(201).json(await service.createStaff(req.body)); } catch (e) { next(e); } };
exports.list   = async (req, res, next) => { try { res.json(await service.listStaff(req.query)); } catch (e) { next(e); } };
exports.get    = async (req, res, next) => { try { res.json(await service.getStaff(req.params.id)); } catch (e) { next(e); } };
exports.update = async (req, res, next) => { try { res.json(await service.updateStaff(req.params.id, req.body)); } catch (e) { next(e); } };
exports.remove = async (req, res, next) => { try { res.json(await service.removeStaff(req.params.id)); } catch (e) { next(e); } };
