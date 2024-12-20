import string

def process_file(input_file, output_file):
    # Define the punctuation characters
    punctuation = string.punctuation

    # Define the ASCII range
    ascii_chars = set(chr(i) for i in range(128))
    
    with open(input_file, 'rb') as infile, open(output_file, 'w', encoding='utf-8') as outfile:
        # Read the file in binary mode to handle unknown encoding
        content = infile.read()
        
        # Decode the binary content using 'ascii' with 'ignore' to skip non-ASCII characters
        filtered_content = content.decode('ascii', errors='ignore')
        
        filtered_content = ' '.join(filtered_content.lower().split())

        filtered_content = ''.join(c for c in filtered_content if c not in punctuation)
        
        # Write the processed content to the output file
        outfile.write(filtered_content)



# Example usage
input_file = 'english.100MB'
output_file = 'corpus.txt'
process_file(input_file, output_file)