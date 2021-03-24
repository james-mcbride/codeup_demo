package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @RequestMapping(path = "/{operation}/{num1}/{operationWord}/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int num1,@PathVariable int num2,@PathVariable String operation,@PathVariable String operationWord) {
        if (operation.equalsIgnoreCase("add")){
            return num1 + " + "+num2+" = "+ (num1+num2);
        }else if(operation.equalsIgnoreCase("subtract")){
            return num1 + " - "+num2+" = "+ (num1-num2);
        } else if(operation.equalsIgnoreCase("multiply")){
            return num1 + " * "+num2+" = "+ (num1*num2);
        } else if (operation.equalsIgnoreCase("divide")){
            double division = (double)num1/num2;
            return num1 + " / "+num2+" = "+ division;
        } else{
            return "Sorry, don't recognize that request!";
        }
    }

    @GetMapping("/rolldice")
    public String rollDiceGuess() {
        return "rollDiceGuess";
    }

    @PostMapping("/rolldice/n")
    public String rollDice(@RequestParam(name = "guess") String guess, Model model) {
        long randomInt = Math.round(Math.random()*5+1);
        model.addAttribute("random", randomInt);
        model.addAttribute("guess", guess);
        String correct;
        if (randomInt==Long.parseLong(guess)){
            correct="correct";
        } else{
            correct="incorrect";
        }
        model.addAttribute("correct", correct );
        return "rollDice";
    }

}
