import db from '../connection.js';
import response from '../response.js';

const getAllFoods = (req, res) => {
    const sql = "SELECT * FROM food;";
    db.query(sql, (error, result) => {
        if (error) throw err;
        // if (error) response(500, "invalid", "error", res);
        response(200, result, "Foods", res);
    })
}

export {
    getAllFoods
}