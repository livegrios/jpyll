# This script print the first 50 int numbers
# making a pause of 0.5 seconds on each iteration:

import sys
import time

try:
    
    max_value = int(sys.argv[1])
    print('Parameter Received [max_value=%d]' % max_value)
    for i in range(1, max_value + 1):
        print(i)

except Exception as e:
    print(str(e))
except Warning as w:
    print(str(w))
except RuntimeWarning as rw:
    print(str(rw))