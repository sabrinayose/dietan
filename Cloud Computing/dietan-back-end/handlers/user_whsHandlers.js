import db from '../connection.js';
import response from '../response.js';

const getAllUser_wh = (req, res) => {
    const sql = "SELECT * FROM user_wh;";
    db.query(sql, (error, result) => {
        if (error) throw err;
        // if (error) response(500, "invalid", "error", res);
        response(200, result, "Weight-Height 's", res);
    })
}

const createUser_wh = (req, res) => {
    const { userId, weight, height } = req.body;

    console.log(req.body);
    const sql = `INSERT INTO user_wh (userId, weight, height) VALUES ('${userId}', '${weight}', '${height}');`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                userWhId: result.insertId,
            }
            response(200, data, "Weight-Height successfully added", res);
        }
        
    })
}

const updateUser_wh = (req, res) => {
    const { weight, height } = req.body;
    const { id } = req.params;
    const sql = `UPDATE user_wh SET weight = '${weight}', height = '${height}' WHERE userId = ${id};`;
    
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);

        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                message: result.message,
            }
            response(200, data, "Weight-Height successfully updated", res);
        } else {
            response(404, "userId not found", "error", res);
        }
    })
}

export {
    getAllUser_wh,
    createUser_wh,
    updateUser_wh
}