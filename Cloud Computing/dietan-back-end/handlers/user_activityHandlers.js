import db from '../connection.js';
import response from '../response.js';

const getAllUserActivities = (req, res) => {
    const sql = "SELECT * FROM user_activity;";
    db.query(sql, (error, result) => {
        if (error) throw err;
        // if (error) response(500, "invalid", "error", res);
        response(200, result, "User Activity", res);
    })
}

const createUserActivity = (req, res) => {
    const { userId, activity, activityLevel } = req.body;

    console.log(req.body);
    const sql = `INSERT INTO user_activity(userId, activity, activityLevel) VALUES (${userId}, '${activity}', '${activityLevel}');`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                userActivityId: result.insertId,
            }
            response(200, data, "User Medical History successfully added", res);
        }
        
    })
}

export {
    getAllUserActivities,
    createUserActivity,
}