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

