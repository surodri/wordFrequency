# Word Frequency in decreasing order

## Tech stack
- jdk 18.0.2

### Input:

Please add your text input to the following file.
frequency/src/main/resources/text.txt

### Output:

After running the application, the output will be printed in the command line.
The frequencies will be in decreasing order. The words with the same frequency won't be.

### Run application
`./mvnw spring-boot:run`

### Run tests
`./mvnw test`

### Assumptions considered for word formats:

A word is assumed to be a chunk of text with characters of a-z, A-Z, 9-0.
The characters can be united by an apostrophe, dash or underscore.

Formats:

- Apple apple         //Differences in capitals are considered different words. Here the frequencies would be 1, 1 vs 2.
- with-dash
- with_underscore
- 1-10                //digits with dashes
- cat's               //words with apostrophe

Examples:

the cat is in the bag

2 the
1 in
1 cat
1 bag
1 is

the cat's toy is on the shelf

2 the
1 cat's
1 is
1 toy
1 on
1 shelf

the cat is_in the fun-bag 1-10

2 the
1 cat
1 is_in
1 fun-bag
1 1-10