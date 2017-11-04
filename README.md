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