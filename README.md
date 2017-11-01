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