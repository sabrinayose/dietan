import db from '../connection.js';
import response from '../response.js';


const createUser_daily_meal = (req, res) => {
    const { foodId, userId } = req.body;

    console.log(req.body);
    const sql = `INSERT INTO user_daily_meal (foodId, userId) VALUES ('${foodId}', '${userId}');`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                userDailyMealId: result.insertId,
            }
            response(200, data, "Daily meal successfully added", res);
        }
        
    })
}

export {
    createUser_daily_meal
}