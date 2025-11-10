module.exports = (err, req, res, next) => {
    const status = /non trouvé|not found/i.test(err.message) ? 404 : 400;
    res.status(status).json({ error: err.message });
};
