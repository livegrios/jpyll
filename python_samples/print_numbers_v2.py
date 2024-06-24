import sys

try:
    
    max_value = int(sys.argv[1])
    print('Argument Received [max_value=%d]' % max_value)
    for i in range(1, max_value + 1):
        print(i)

except Exception as e:
    print(str(e))
except Warning as w:
    print(str(w))
except RuntimeWarning as rw:
    print(str(rw))