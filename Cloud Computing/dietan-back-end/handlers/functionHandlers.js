import db from '../connection.js';
import response from '../response.js';

const getBmi = (req, res) => {
    const { id } = req.params;
    const sql = `SELECT weight, height FROM user_wh WHERE userId = ${id};`;
    db.query(sql, (error, result) => {
        if (error) throw err;

        console.log(result);
        const weight = result[0].weight;
        const height = result[0].height/100;

        console.log(weight);
        console.log(height);

        const bmi = weight/height;
        console.log(bmi);
        response(200, bmi, "BMI", res);
    })
}

const getRecommendation = (req, res) => {
    const allCalories = [];
    const allRecommendation = [];
    let recommend ="";
    const calory = 500;
    let sortedCalories = 0;
    const sql = `SELECT * FROM food;`;

    db.query(sql, (error, result) => {
        if (error) throw err;
        console.log(result);

        sortedCalories = result.sort(function(a, b){return a.calories - b.calories});
        allCalories.push(sortedCalories);
        console.log(allCalories);
    
        for (let j = 0; (j < allCalories[0].length); j++) {
            var makanan = 0;
            const recommendation = [];

            for (let k = j; k < allCalories[0].length; k++) {
                if (makanan <= calory) {
                    if ((makanan + allCalories[0][k].calories) < calory) {
                        makanan = makanan + allCalories[0][k].calories;
                        recommend = "Food Name : ".concat(allCalories[0][k].foodName, " | Calories : ", allCalories[0][k].calories);
                        recommendation.push(recommend);
                    }
                    
                }
            }
            allRecommendation.push(recommendation);
        };
        console.log(allRecommendation);
        response(200, allRecommendation, "Food Recommendation", res);
    })
}

const getUserInputCalories = (req, res) => {
    const { id } = req.params;
    let calory = 500;
    const sql = `SELECT food.calories 
        FROM user_daily_meal 
        JOIN food ON user_daily_meal.foodId = food.foodId 
        WHERE user_daily_meal.userid=${id}
        AND SUBSTRING(user_daily_meal.date, 1,10)=CURDATE();`;
    db.query(sql, (error, result) => {
        if (error) throw err;

        for(let i = 0; i < result.length; i++) {
            calory = calory - result[i].calories;
        }
        console.log(calory);

        // console.log(result);
        response(200, calory, "Remaining Calories", res);
    })
}

export {
    getBmi,
    getRecommendation,
    getUserInputCalories
}