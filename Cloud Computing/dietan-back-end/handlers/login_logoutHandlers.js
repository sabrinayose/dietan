import db from '../connection.js';
import response from '../response.js';

const signupUser = (req, res) => {
    const { userName, password, dob, gender, email } = req.body;

    console.log(req.body);
    const sql = `INSERT INTO user (userName, password, dob, gender, email) VALUES ('${userName}', '${password}', '${dob}', '${gender}', '${email}');`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                userId: result.insertId,
            }
            response(200, data, "Signed up successfully", res);
        }
        
    })
}

const loginUser = (req, res) => {
    const { userName, password } = req.body;
    const sql = `SELECT * FROM user WHERE userName LIKE '${userName}' and password LIKE '${password}'`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result.length > 0) {
            response(200, result, "login berhasil", res);
        } else {
            response(500, "invalid username or password", "error", res);
        }
    })
}

const logoutUser = (req, res) => {
    res.send("logout")
}

export {
    signupUser,
    loginUser,
    logoutUser,
}