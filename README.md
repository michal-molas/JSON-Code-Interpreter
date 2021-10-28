# JSON-Code-Interpreter

This is a program that lets you execute a simple program written in JSON.

## About program

The main goal of this program is to interpret code written in a specific syntax in JSON.
Available instructions are
- Addition

```
"type":"Add",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is value of argument1 + argument2.
- Subtaction

```
"type":"Sub",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is value of argument1 - argument2.
- Multiplication

```
"type":"Mul",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is value of argument1 * argument2.
- Division

```
"type":"Div",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is value of argument1 / argument2.
It will throw an exception if the value of argument2 is 0.
- Less

```
"type":"<",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is 1 if argument1 < argument2 and 0 otherwise.
- Less or equal

```
"type":"<=",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is 1 if argument1 <= argument2 and 0 otherwise.
- Greater

```
"type":">",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is 1 if argument1 > argument2 and 0 otherwise.
- Greater or equal

```
"type":">=",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is 1 if argument1 >= argument2 and 0 otherwise.
- Equal

```
"type":"==",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is 1 if argument1 == argument2 and 0 otherwise.
- And

```
"type":"And",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is 1 if argument1 and argument2 are non-zero and 0 otherwise.
- Or

```
"type":"Or",
  "arg1":{
    [argument1]
  },
  "arg2":{
    [argument2]
  }
```
The result is 1 if argument1 or argument2 is non-zero and 0 otherwise.
- Not

```
"type":"Not",
  "arg":{
    [argument]
  },
```
The result is 1 if argument == 0 and 0 otherwise.
- If

```
"type":"If",
  "cond": {
    [condition_instruction]
  },
  "trueBlock": {
    [instruction_true]
  },
  "falseBlock": {
    [instruction_false]
  }
```
The falseBlock is optional.\
The result is the value of instruction_true if condition_instruction != 0, instruction_block if there is one and 0 otherwise.\
It will perform instruction_true if condition_instruction != 0 and instruction_false otherwise (if it exists).
- While

```
"type":"While",
  "cond": {
    [condition_instruction]
  },
  "block": {
    [instruction]
  }
```
The result is always 0.\
It will perform instruction as many times while condition_instruction != 0.
- Block

```
"type":"Block",
  "insts": [
    [instruction1],
    [instruction2],
    ...
    [instructionN]
  ]
```
The result is the value of instructionN if N > 0 and 0 if N = 0.\
- Number

```
"type":"Num",
  "value":[float_value]
```
The result is float_value.\
- Variable

```
"type":"Var",
  "name":[var_name]
```

The result is the value of th variable with name var_name if it already has an assigned value or 0 otherwise.\
The var_name must be a string with a proper name, that would work in java (Eg. "123abc", "12", "" will throw an exception).
- Assign

```
"type":"Assign",
  "name":[var_name],
  "value":{
    [instruction]
  }
```

The result is the value of instruction.\
The value of instruction will be assigned to variable var_name.\
The var_name must be a string with a proper name, that would work in java (Eg. "123abc", "12", "" will throw an exception).
- True

```
"type":"True"
```
The result is 1.
- False

```
"type":"False"
```
The result is 0.

### Important information
- Every instruction returns a value, so every instruction can be put everywhere as an instruction.
- Every instruction (except for While and If, whose behaviour i have described above as well as And and Or)\
will perform all of the instructions it contains exactly once.\
The And and Or instructions might not perform their second instruction if the result is known after the first one (lazy evaluation).
- The instructions described as conditions will be performed before checking their value.
- All numerical values are treated as float numbers, so checking equallity may somtimes cause problems, due to floating point precision.
- The code must be enclosed in curly bracets.
- The printed result will be the result of the last instruction,\
so for example you can start with a Block and put a Var with the result as the last instruction.

## Other functionality
The program also makes a compilable file in java (puts it into ProgramsJava folder),\
which performs the same actions as the json code and gives the same results.\

It also creates a copy of the original json and puts it into CreatedPrograms folder.

## Example programs
In the folder Programs, there are a few example programs written in json.
- EToX.json - calculates e^x
- GCD.json - calculates gratest common divisor of two numbers
- Pythagoras.json - calculates c, where c^2 = a^2 + b^2, given a and b
- PiLeibnitz.json - calculates the value of pi with Leibniz formula
- Fibonacci.json - calculates n-th Fibonacci number

## How to run
The program uses moshi (https://github.com/square/moshi) for the json operations.\
The easiest way to run is be using IntelliJ and going to\
File -> ProjectStructure -> Libraries\
and adding
- com.squareup.moshi
- com.squareup.moshi-adapter

You might have to restart IntelliJ after that.
