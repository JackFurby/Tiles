## Tiles
Tiles is a markdown editor which is made in Java. It is currently not in a functional state. This is also my first large Java project and therefore may not include the highest quality code (but it will be improving over time).

### Libraries
For the conversion of md files to html I have used commonmark-java. More information can be found here: https://github.com/atlassian/commonmark-java

For exporting to PDF I used iText. More information can be found here: https://developers.itextpdf.com/

### Current development
Currently there is a interface and input is converted to html before being rendered. A few buttons in the menu work.

### To do
* Help menu
* CSS for application (add as application is developed)
* CSS for output (WIP)
* Preferences / settings (WIP)
* Config (WIP)
* Syntax highlighting (will do after V1)
* Spell check (will do after V1)
* File menu
    * Print (will do after V1)

### Known issues
* MD images cannot be rendered to PDF as document is in HTML and renderer takes XML
* Tables do not respond to all CSS in outputArea
