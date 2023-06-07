import express from 'express';
import bodyParser from 'body-parser';
import foodsRoutes from './routes/foods.js';
import db from './connection.js';
import response from './response.js';

const app = express();
const PORT = 5000;

app.use(bodyParser.json());

app.use('/foods', foodsRoutes);

app.get('/', (req, res) => {
    console.log('[TEST]!');

    res.send("Hello from Homepage");
} );

// app.get('/foodupdate/:foodId', (req, res) => {
//     const { foodName, calories } = req.body;
//     const { foodId } = req.params;
//     const sql = `UPDATE food SET foodName = '${foodName}', calories = '${calories}' WHERE foodId = ${foodId};`;
    
//     db.query(sql, (error, result) => {
//         // console.log(result);
//         // response(200, result, "Food successfully updated", res);
//         if (error) response(500, "invalid", "error", res);

//         if (result?.affectedRows) {
//             const data = {
//                 isSuccess: result.affectedRows,
//                 message: result.message,
//             }
//             response(200, data, "Food successfully updated", res);
//         } else {
//             response(404, "user not found", "error", res);
//         }
//     })
//     // console.log("Hi");
// })

app.listen(PORT, () => console.log(`Server running on port : http://localhost:${PORT}`));