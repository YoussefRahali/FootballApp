const router = require('express').Router();
const c = require('../controllers/staff.controller');

router.get('/', c.list);
router.post('/', c.create);
router.get('/:id', c.get);
router.put('/:id', c.update);
router.delete('/:id', c.remove);

module.exports = router;
