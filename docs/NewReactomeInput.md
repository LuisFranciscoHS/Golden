# Reactome Analysis tool v2

# Improvements

* Use Google Guava Stopwatch class to measure input preprocessing performance.
    
    It is based on the System.nanoTime(), instead of System.currentTimeMillis(), 
    which measures the elapsed wall-clock time. In contrast, System.nanoTime() returns the current value of the most 
    precise available system timer, which is specifically developed to measure elapsed time.
    
 * Faster reading of expression input.
  
  Traverse the input fewer times. Currently, it is traversing all input at least 3 times using:
 split, replaceAll, and StringTokenizer. 
 
 * Added possibility to specify proteoforms.
    * Add support for PEFF format.
    * Added support for Protein Ontology (PRO) format.
    * Added support for a simple custom proteoform format.
    

## Definitions

A __proteoform__ is a protein with a set of post translational modifications.

A __protein__ is identified by a standard protein identifier such as UniProt accession or Ensembl.
A _post-translational modification_(PTM) is uniquely identified by two elements: type and coordinate in the protein sequence.

The PTM __type__ is specified following the PSI-MOD ontology. No other types are supported. 

The PTM __coordinate__ is an integer number indicating the position in the protein amino acid sequence where the post-translational modification occur.

__Matching__ is deciding if an input proteoform corresponds to a proteoform in the database.
An input proteoform can be connected to 0 or more stored proteoforms. They can match perfectly or with a margin of error. 

A PTM __unkown coordinate__ in the input is specified by an integer 0 or null. 

A __perfect PTM match__ is when two PTMs have the exact same coordinate and type. 

A __perfect proteoform match__ is when an input proteoform and a stored proteoform fullfill these conditions: 
 - The protein accession is the same.
 - The isoform is the same. If no isoform is specified, the canonical is taken. 
 - For each PTM in the input, there is perfect PTM match in the stored proteoform.

Having an integer _n_. A __range__ for PTM coordinates is the set of protein sequence coordinates that fall in the closed interval [n-1, n+1].

An __imperfect match__ is when an input proteoform and a stored proteoform fullfill these conditions: 
- The protein accession is the same.
- The isoform is the same.
- At least one input PTM has no perfect match in the stored proteoform. 
For each PTM in the input, there is a PTM in the stored proteoform with:
    - The input PTM type is less specific than the stored PTM, using the PSI-MOD hierarchy (the input type is a parent of the stored type). 
    - The coordinates fulfill at least one of these conditions:
        - Either the input PTM coordinate falls in the range of the stored PTM coordinate.
        - Or the input PTM coordinate is unknown.

An input proteoform is not matched (not considered found) when at least one of the following conditions happen:
- The identifier is not found in Reactome.
- The specific isoform is not found in Reactome.
- One input PTM has no perfect or imperfect match:
    - At least one input PTM is more specific than the stored PTMs. In other words, the input PTM type is a child of the stored PTM type in the ontology hierarchy tree.
    - At least one input PTM with the same coordinate was not found in the stored proteoform.

## Input Formats

### Identifiers list

#### Gene name list
#### UniProt accession list
#### Gene NCBI / Entrez list
#### Small molecules (ChEBI)
#### Small molecules (KEGG)
#### Simple Proteoforms list

### Expression values
* Files can be in one line or multiple lines.
* File contains multiple columns. The first column in each row contains the identifier. All the other columns must contain
expression values as floating point numbers.


* Identifiers can be repeated in multiple lines?

#### Microarray data
#### Metabolomics data
#### Cancer Gene Census
#### Simple proteoforms list with expression values

### Other formats
#### PEFF
#### Protein Ontology
#### GPMDB


#### Simple proteoform list

Each line of the file corresponds to a single proteoform.
A line consists of two fields separated by ';'. First a Uniprot Accession and second a set of PTMs.
The second field is optional. Lacking a PTM set means a proteoform without modifications.
The PTM set specifies presents each PTM separated by a ','. 
Each PTM is specified using a modification identifier and an integer coordinate, separated by ':'(semicolon). 
The modification identifier is a 5 digit id from the Protein Modification Onthology [\[2\]](#references).
For example: "133:00046, which corresponds to [O-phospho-L-serine](https://www.ebi.ac.uk/ols/ontologies/mod/terms?iri=http%3A%2F%2Fpurl.obolibrary.org%2Fobo%2FMOD_00046) at the coordinate 133. 

Single proteoform examples:
- A single protein with no modifications
~~~~
P00519
~~~~
- A protein with one PTM. The two fields are separated by a ';'
~~~~
P16220;133:00046
~~~~
- A protein and a set of PTMs separated by ','. The PTMs can be ordered randomly.
~~~~
P62753;235:00000,236:00000,240:00000
~~~~
In case the PTM type is not known, the modification id used is "00000". For example: "00000:245".
In case the PTM coordinate is not known, the integer used is 0.

<br>File example:
~~~~
P10412-1
P10412
P56524;559:00916
P04637;370:00084,382:00084
P56524;246:00916,467:00916,632:00916
P12345-2;246:00916,467:00916,632:00916
Q1AAA9
O456A1
P4A123
A0A022YWF9
A0A022YWF9;246:00916,467:00916,632:00916
~~~~

#### Protein Ontology (TODO)

#### PEFF (TODO)

#### GPMDB (TODO)

### Implementation (TODO)

The input type is decided using the first 5 lines of the file, without counting headers.

An Ensembl identifier will be mapped to UniProt accession. Ensembl is only allowed in the GPMDB format.

PTMS are stored as radix tree leaves attributes.
Save them as a sorted set ordered by mod type and then coordinate. 
Because PTMs with the same type appear in more than one coordinate, but, theoretically, 
one coordinate can not contain more than one PTM at once. 

Sort the PTMs in the input, by mod and then by coordinate.

First search for the protein accession in the radix tree.
Then find the

An unknown PTM coordinate is stored as null, to avoid counting the 0 in the range of near coordinates.

### Analysis

#### Input reader

#### Input matching

#### Intermediate data structure

#### Results analysis

# References
\[1\] [UniProt: the universal protein knowledgebase. Nucleic Acids Res. 45: D158-D169 (2017)](http://dx.doi.org/doi:10.1093/nar/gkw1099) <br>
\[2\] [The PSI-MOD community standard for representation of protein modification data. Nature Biotechnology 26, 864 - 866 (2008)](http://www.nature.com/nbt/journal/v26/n8/full/nbt0808-864.html) <br>

hp