/**
 * A class that represents a rational number in normal form where the numerator
 * and the denominator share no common factors and only the numerator
 * can be negative.
 * 
 * @author Charles Hoot 
 * @version 4.0
*/

public class Rational
{
    // PUT PRIVATE DATA FIELDS HERE
    private int numer;
    private int denom;

    /**
     * The default constructor for objects of class Rational.  Creates the rational number 1.
     */
    public Rational()
    {       
        this.numer = 1;
        this.denom = 1;
    }

    /**
     * The alternate constructor for objects of class Rational.  Creates a rational number equivalent to n/d.
     * @param n The numerator of the rational number.
     * @param d The denominator of the rational number.
     */    
    public Rational(int n, int d)
    {
        if (d == 0) {
            throw new ZeroDenominatorException("Denominator cannot be zero");
        }

        int gcd = gcd(Math.abs(n), Math.abs(d));

        if (d < 0) {
            this.numer = -n/gcd;
            this.denom = -d/gcd;
        } else {
            this.numer = n/gcd;
            this.denom = d/gcd;
        }
    }
    
    /**
     * Get the value of the Numerator
     *
     * @return     the value of the numerator
     */

    public int getNumerator()
    {
        return this.numer;
    }
    
    /**
     * Get the value of the Denominator
     *
     * @return     the value of the denominator
     */

    public int getDenominator()
    {
        return this.denom;
    }

    /**
     * Negate a rational number r
     * 
     * @return a new rational number that is negation of this number -r
     */    

    public Rational negate()
    {               
        return new Rational(-this.numer, this.denom);
    }

    /**
     * Invert a rational number r 
     * 
     * @return a new rational number that is 1/r.
     */    
    public Rational invert()
    {               
        return new Rational(this.denom, this.numer);
    }

    /**
     * Add two rational numbers
     *
     * @param other the second argument of the add
     * @return a new rational number that is the sum of this and the other rational
     */    

    public Rational add(Rational other)
    {       
        int commonDenom = this.denom * other.denom;
        return new Rational((this.numer * other.denom) + (other.numer * this.denom), commonDenom);
    }
    
     /**
     * Subtract a rational number t from this one r
     *
     * @param other the second argument of subtract
     * @return a new rational number that is r-t
     */    
    public Rational subtract(Rational other)
    {               
        int commonDenom = this.denom * other.denom;
        return new Rational((this.numer * other.denom) - (other.numer * this.denom), commonDenom);
    }

    /**
     * Multiply two rational numbers
     *
     * @param other the second argument of multiply
     * @return a new rational number that is the sum of this object and the other rational.
     */    
    public Rational multiply(Rational other)
    {       
        return new Rational(this.numer * other.numer, this.denom * other.denom);
    }
        
 
     /**
     * Divide this rational number r by another one t
     *
     * @param other the second argument of divide
     * @return a new rational number that is r/t
     */    
    public Rational divide(Rational other)
    {               
        return new Rational(this.numer * other.denom, this.denom * other.numer);

    }
     
      
 /**
     * Put the rational number in normal form where the numerator
     * and the denominator share no common factors.  Guarantee that only the numerator
     * can be negative.
     *
     */
    private void normalize()
    {

        int gcd = gcd(Math.abs(this.numer), Math.abs(this.denom));
        if (denom < 0) {
            this.numer = this.numer * -1;
            this.numer = this.denom * -1;
        }
        this.numer = this.numer/gcd;
        this.denom = this.denom/gcd;

    }
    
    /**
     * Recursively compute the greatest common divisor of two *positive* integers
     *
     * @param a the first argument of gcd
     * @param b the second argument of gcd
     * @return the gcd of the two arguments
     */
    private int gcd(int a, int b)
    {
        int result = 0;
        if(a<b)
            result = gcd(b,a);
        else if(b==0)
            result = a;
        else
        {
            int remainder = a % b;
            result = gcd(b, remainder);
        }
        return result;
    }
}
