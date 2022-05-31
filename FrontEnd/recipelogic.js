"use strict";
//Selectors
let enterButton = document.querySelector("#enter_recipe")
let updateButton = document.querySelector("#update_recipe");
let deleteButton = document.querySelector("#delete_recipe");
let viewAllButton = document.querySelector("#view_all_recipes");
let recipeReadout = document.querySelector("#recipe_readout_div")
let recipeID = document.querySelector("#recipe_id_input");
let recipeName = document.querySelector("#recipe_name_input");
let ingredients = document.querySelector("#ingredients_input");
let dietFriendly = document.querySelector("#diet_friendly_input");

//Functions

let revealButtons = () => {
    //leverages truthy falseyness as "" is string false value
    //missed .value off of 3/4, took ages to fix.
     if (recipeID.value && !recipeName.value && !ingredients.value && dietFriendly.value=="N/A")   {
         //console.log("something or other"); //not working, not sure why
         deleteButton.classList.remove("hide");
         enterButton.classList.add("hide");
         updateButton.classList.add("hide");
     }   else if    (!recipeID.value && recipeName.value && ingredients.value && dietFriendly.value !=="N/A")    {
        deleteButton.classList.add("hide");
        enterButton.classList.remove("hide");
        updateButton.classList.add("hide");
    }   else if (recipeID.value && recipeName.value && ingredients.value && dietFriendly.value !=="N/A") {
        deleteButton.classList.add("hide");
        enterButton.classList.add("hide");
        updateButton.classList.remove("hide");
    }   else    {
        deleteButton.classList.add("hide");
        enterButton.classList.add("hide");
        updateButton.classList.add("hide");
    }
 }
// used text content as suggested by Anoush. Other solutions did not work to separate text.
let displayResult = (data) =>   {
    recipeReadout.innerHTML="";
    for (let recipe of data) {
        const recipeDiv = document.createElement("div");
        recipeDiv.innerHTML=`Recipe ID: ${recipe.id}<br/> Recipe Name: ${recipe.recipeName}<br/> Ingredients Used: ${recipe.ingredients}<br/> Diet Friendly? ${recipe.dietFriendly}<br/>`;
        recipeReadout.appendChild(recipeDiv);
    }
}

//API
let createRecipe = () =>  {

    let recipe = {
        "recipeName":recipeName.value,
        "ingredients":ingredients.value,
        "dietFriendly":dietFriendly.value
    }

    axios.post("http://localhost:8080/recipes/create/", recipe)
        .then((response) =>  {
            console.log(recipe)
            viewAllRecipe();
        })
        .catch((error) => {
            console.error(error);
        })
    }

let updateRecipe = () =>  {

    let recipe = {
        "recipeName":recipeName.value,
        "ingredients":ingredients.value,
        "dietFriendly":dietFriendly.value
    }

    axios.put(`http://localhost:8080/recipes/update/${recipeID.value}`, recipe)
        .then((response) =>  {
            viewAllRecipe();
        })
        .catch((error) => {
            console.error(error);
        })
    }

let viewAllRecipe = () =>  {
    axios.get("http://localhost:8080/recipes/getAll/")
        .then((response) => {
            displayResult(response.data);
        })
        .catch((error) => {
            console.error(error);
        })
    }

    //trying to get it to spit out what last went in, but... it's hard.
let viewLastRecipe = () =>  {
    recipeReadout.innerHTML="";
    axios.get(`http://localhost:8080/recipes/getById/${recipeID.value}`)
        .then((response) =>  {
            recipeID = "";
            recipeName = "";
            ingredients = "";
            dietFriendly = "";
            displayResult;
        })
        .catch((error) => {
            console.error(error);
        })
    }

let deleteRecipe = () =>  {
    axios.delete(`http://localhost:8080/recipes/delete/${recipeID.value}`)
        .then((response) =>  {
            viewAllRecipe();
        })
        .catch((error) => {
            console.error(error);
        })
    }

//Event Listeners

enterButton.addEventListener("click", createRecipe);
updateButton.addEventListener("click", updateRecipe);
deleteButton.addEventListener("click", deleteRecipe);
viewAllButton.addEventListener("click", viewAllRecipe);
recipeID.addEventListener("change", revealButtons);
recipeName.addEventListener("change", revealButtons);
ingredients.addEventListener("change", revealButtons);
dietFriendly.addEventListener("change", revealButtons);