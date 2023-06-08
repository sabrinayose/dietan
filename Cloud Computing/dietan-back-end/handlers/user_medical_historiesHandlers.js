import db from '../connection.js';
import response from '../response.js';

const getAllUserMedicalHistories = (req, res) => {
    const sql = "SELECT * FROM user_medical_history;";
    db.query(sql, (error, result) => {
        if (error) throw err;
        // if (error) response(500, "invalid", "error", res);
        response(200, result, "User Medical History", res);
    })
}

const createUserMedicalHistory = (req, res) => {
    const { userMedicalHistoryId, userId, allergy, avoidedFood } = req.body;

    console.log(req.body);
    const sql = `INSERT INTO user_medical_history VALUES ('${userMedicalHistoryId}', '${userId}', '${allergy}', '${avoidedFood}');`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                userMedicalHistoryId: result.insertId,
            }
            response(200, data, "User Medical History successfully added", res);
        }
        
    })
}

const updateUserMedicalHistory = (req, res) => {
    const { allergy, avoidedFood } = req.body;
    const { id } = req.params;
    const sql = `UPDATE user_medical_history SET allergy = '${allergy}', avoidedFood = '${avoidedFood}' WHERE userMedicalHistoryId = ${id};`;
    
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);

        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                message: result.message,
            }
            response(200, data, "User Medical History successfully updated", res);
        } else {
            response(404, "userMedicalHistoryId not found", "error", res);
        }
    })
}

export {
    getAllUserMedicalHistories,
    createUserMedicalHistory,
    updateUserMedicalHistory
}