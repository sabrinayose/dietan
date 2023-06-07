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

const createFood = (req, res) => {
    const { foodId, foodName, calories } = req.body;

    console.log(req.body);
    const sql = `INSERT INTO food VALUES ('${foodId}', '${foodName}', '${calories}');`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);
        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                foodId: result.insertId,
            }
            response(200, data, "Food successfully added", res);
        }
        
    })
}

const getFoodDetail = (req, res) => {
    const { id } = req.params;
    const sql = `SELECT * FROM food WHERE foodId = ${id};`;
    db.query(sql, (error, result) => {
        if (error) throw err;
        // if (error) response(500, "invalid", "error", res);
        response(200, result, "Food Detail", res);
    })
}

const deleteFood = (req, res) => {
    const { id } = req.params;
    const sql = `DELETE FROM food WHERE foodId = ${id};`;
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);

        if (result?.affectedRows) {
            const data = {
                isDeleted: result.affectedRows,
            }
            response(200, data, "Food successfully deleted", res);
        } else {
            response(404, "user not found", "error", res);
        }
    })
}

const updateFood = (req, res) => {
    const { foodName, calories } = req.body;
    const { id } = req.params;
    const sql = `UPDATE food SET foodName = '${foodName}', calories = '${calories}' WHERE foodId = ${id};`;
    
    db.query(sql, (error, result) => {
        if (error) response(500, "invalid", "error", res);

        if (result?.affectedRows) {
            const data = {
                isSuccess: result.affectedRows,
                message: result.message,
            }
            response(200, data, "Food successfully updated", res);
        } else {
            response(404, "foodId not found", "error", res);
        }
    })
}

export {
    getAllFoods,
    createFood,
    getFoodDetail,
    deleteFood,
    updateFood
}