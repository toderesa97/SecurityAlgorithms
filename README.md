# Security algorithms

This repo aims to cover all the things I'm learning about this amazing world.

 The first commit tried to show the vulnerabilities of hashing functions for short messages. A brute force program, the committed one,  can easily
 permute through all the variations, encrypt the variation with SHA256 and compare it with the given hash. If matches 
 then we have just found out the message!, though salting the message is highly recommended to avoid rainbow attacks, companies should also ask
 the users to set passwords of 12 characters (at least, which generates 1293304320 possibilities and 
 supposing the attacker has figured out the characters which form your password),
  combine alphanumeric chars (such as %.@#), numbers, 
 and upper and lower case. Those measures make, without any doubt, unfeasible a brute force attack, because is not only
 calculating all the variations but adding that piece of randomness (computationally **UNFEASIBLE**)
 
#### Cracker class
Contains a set of functions related to basic cipher algorithm, such as affine cipher or caesar. Besides encrypting the message
 is also possible to decrypt it. **Methods:**
 
    - encrypt_shift_cipher (String x, int key)
 Where x represents the message. Mathematical expression to encrypt: ``y=e(x)=(x+key)mod(26)``
 
    - encrypt_affine_cipher (String x, int a, int b)
 Where x represents the message. a and b are the keys. The mathematical expression which is going to be used
 for encrypting the message is: ``y=e(x)=(a*x+b) mod(26)``. Caveat: ``a and 26`` must be relatively primes, ie: ``gcd(a,26)=1``
 
    - decrypt_shift_cipher (String y)
 Returns a set of possible understandable sentences based on a set of common words in english 
 (chosen based on the frequency analysis of characters). ```y``` represents the encrypted message. Mathematical expression 
 used to decrypt: ``x=d(y)=(y-key)mod(26)`` [Solving for ``x`` in ``e(x)`` function]
 
    - decrypt_affine_cipher (String y)
  Returns a set of possible understandable sentences based on a set of common words in english 
  (chosen based on the frequency analysis of characters). ```y``` represents the encrypted message. The mathematical
  expression used to decrypt: ``x=d(y)=(inv(a)*(y-b))mod(26)`` [Solving for ``x`` in ``e(x)`` function]
  
  **Frequency of characters (English)**
  
  ![alt text](https://upload.wikimedia.org/wikipedia/commons/d/d5/English_letter_frequency_%28alphabetic%29.svg)
  
  
  ### Appendix
  
  ##### Method ``calculateInverse(int number, int zp) ``
  *Basic approach*
  
  Calculating the multiplicative inverse is a crucial fact for the RSA cryptosystem. Z3 is an arithmetic such that works
  with ``{0,1,2}``. Calculating an inverse multiplicative of a number (``a``) is figuring out a number ``i`` 
  such that ``(a*i)mod(p)=1``. For this purpose, ``a`` and ``i`` must be relatively primes. Otherwise it won't be possible to 
  find ``i``. Trial and error method is possible but for large integers is suitable the use of Euclidean and Bézout's theorem
   (actually is a combination, coalesced on the extended euclidean algorithm)
  
  **Example**: calculate the inverse multiplicative of 5 in Z13 (not using the said theorem)
  
  Since ``gcd(5,13)=1`` exists an ``i`` such that ``(5*i)mod(13)=1``. We know that:
  
  ``5*i=13*q+1  =>  5*i-1=13*q   =>   (5*i-1)/13=q``
  
  It's a little bit cumbersome to try with values of ``i`` such that ``5*i-1 < 13``, since denominator will be always greater than numerator.
  So, ``i`` must start from ``i=ceil(12/5)=3``. First approach could be:
  
  
      for (int i = (int) Math.ceil((zp-1)/a); i < zp; i++) {
    
          if (Math.floorMod(a*i,zp) == 1) return i;
      }
      return -1;
      
  Complexity is ``O(zp)``. This method is not efficient for large inputs but it works. We need the extended euclidean algorithm which has a complexity
  of ``O(log(zp))``
  
  -

 *Advanced approach*: using The Extended Euclidean Algorithm
 
 Please, consider reading about the Euclidean and Bézout's theorem before continuing if you are not familiar with.
 
 If ``gcd(a,b)=m``, then exists two numbers ``x and y`` such that ``a*x+b*y=m`` (Bézout's theorem). Applied to our 
 case, we know that ``gcd(a,zp)=1``, therefore, ``a*x+zp*y=1``
 
 Example: calculate the inverse of 2 in Z3: Since ``gcd(2,3)=1`` there's an inverse. Hence, ``2*x+3*y=1``. Rewriting 1,
 ``2*x+3*y=2-1``, again, to express in terms of 2 and 3, ``2*x+3*y=2-(3-2) => 2*x+3*y=2*2-3*1``, so:
 
 ``(2*2+3*(-1))mod(3)=1    => (2*2)mod(3)``, the inverse is 2.
 
 
 
 
 
 
  
  
  
  
  
  
  