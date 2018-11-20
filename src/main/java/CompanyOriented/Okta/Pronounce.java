package CompanyOriented.Okta;

import java.util.*;

public class Pronounce {
    public static void main(String[] args) {
        Pronounce inst = new Pronounce();
//        System.out.println(inst.validate("abc"));
//        System.out.println(inst.validate("aae"));

        System.out.println(inst.validate("eep"));
        System.out.println(inst.validate("houctuh"));
        System.out.println(inst.validate("end"));
//        System.out.println(inst.validate("bontres"));
//        System.out.println(inst.validate("zoggax"));
//        System.out.println(inst.validate("wiinq"));

    }

    public boolean validate(String word) {

        Set<Character> vowelsSet = new HashSet<Character>();
        vowelsSet.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int countVowels = 0, countConsonants = 0, lastChar = -1;
        boolean withVowel = false;

        for (char ch : word.toCharArray()) {

            if (lastChar == ch && ch != 'e' && ch != 'o')
                return false;
            lastChar = ch;
            if (vowelsSet.contains(ch)) {
                withVowel = true;
                countConsonants = 0;
                countVowels++;
                if (countVowels >= 3)
                    return false;
            } else {
                countVowels = 0;
                countConsonants++;
                if (countConsonants >= 3)
                    return false;
            }

        }
        return withVowel;
    }
}
/*
contain >= 1 vowel
3 consecutive vowels X
3 consecutive consonants
2 consecutive same except for ee or oo

if two same
	if not ee or oo
		return false
if 3 consecutive vowel
	return false
if 3 consecutive consonants
	rturn false

 */