
/**
 * A class that has a methond to recursively replaces characters in a String.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class RecursiveStringReplace
{
    /**
     * replace - Replace all instances of one character by another.
     * 
     * @param  s   The string to do the replacement on.
     * @param  from   The character to be replaced.
     * @param  to   the character to change to.
     * @return     A new string with the appropriate characters replaced.
     */
    public String replace(String s, char from, char to)
    {
        // variables
        int index = s.indexOf(from);
        String result = null;

        // base case
        if (index == -1) return s;

        // edge cases (from is at start, or end)
        String start = (index == 0)? "" : s.substring(0, index);
        String end = (index == s.length() - 1)? "" : s.substring(index + 1);

        result = start + to + end;
        return replace(result, from, to);
    }


}
