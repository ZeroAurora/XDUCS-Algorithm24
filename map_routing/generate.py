import random

def generate_pairs(num_pairs):
    pairs = []
    for _ in range(num_pairs):
        pair = (random.randint(1, 87574), random.randint(1, 87574))
        pairs.append(pair)
    return pairs

num_pairs = 1000
pairs = generate_pairs(num_pairs)

for pair in pairs:
    print(f"{pair[0]} {pair[1]}")
