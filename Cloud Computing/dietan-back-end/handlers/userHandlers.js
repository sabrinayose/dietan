import db from '../connection.js';
import response from '../response.js';

const getAllUsers = (req, res) => {
    const sql = "SELECT * FROM user;";
    db.query(sql, (error, result) => {
        if (error) throw err;
        // if (error) response(500, "invalid", "error", res);
        response(200, result, "Users", res);
    })
}

const createUser = (req, res) => {
    const { userId, userName, password, dob, gender, email } = req.body;

    console.log(req.body);
    const sql = `INSERT INTO user VALUES ('${userId}', '${userName}', '${password}', '${dob}', '${gender}', '${email}');`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                userId: result.insertId,
            }
            response(200, data, "User successfully added", res);
        }
    console.log(result);
    })
}

export {
    getAllUsers,
    createUser
}