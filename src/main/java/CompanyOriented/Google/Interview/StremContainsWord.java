package CompanyOriented.Google.Interview;

/*
input: dictionary of words, Stream
output: if stream composites word in dictionary, we output it
e.g.
dict = [abc, def]
stream = e, d, c, a, b, c, s, d, e, f ...
save dict reversed(words) in trie

 */
interface Stream {
    boolean hasNext();

    char next();
}

