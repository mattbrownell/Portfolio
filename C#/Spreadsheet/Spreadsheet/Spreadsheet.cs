using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SpreadsheetUtilities;
using System.Text.RegularExpressions;

namespace SS
{
    /// <summary>
    /// <author>Matt Brownell u0919206</author>
    /// <duedate>10/2/2015</duedate>
    /// Create a spreadsheet.
    /// </summary>
    public class Spreadsheet : AbstractSpreadsheet
    {
        // Dependency Graph backing
        private DependencyGraph dep;
        // Dictionary backing
        private Dictionary<string, Cell> dic;

        /// <summary>
        /// A spreadsheet consists of an infinite number of named cells.
        /// 
        /// A string is a valid cell name if and only if:
        ///   (1) its first character is an underscore or a letter
        ///   (2) its remaining characters (if any) are underscores and/or letters and/or digits
        /// 
        /// A spreadsheet contains a cell corresponding to every possible cell name.  (This
        /// means that a spreadsheet contains an infinite number of cells.)  In addition to 
        /// a name, each cell has a contents and a value.  The distinction is important.
        /// 
        /// The contents of a cell can be (1) a string, (2) a double, or (3) a Formula.  If the
        /// contents is an empty string, we say that the cell is empty.  (By analogy, the contents
        /// of a cell in Excel is what is displayed on the editing line when the cell is selected.)
        /// 
        /// In a new spreadsheet, the contents of every cell is the empty string.
        ///  
        /// The value of a cell can be (1) a string, (2) a double, or (3) a FormulaError.  
        /// (By analogy, the value of an Excel cell is what is displayed in that cell's position
        /// in the grid.)
        /// 
        /// If a cell's contents is a string, its value is that string.
        /// 
        /// If a cell's contents is a double, its value is that double.
        /// 
        /// If a cell's contents is a Formula, its value is either a double or a FormulaError,
        /// as reported by the Evaluate method of the Formula class.  The value of a Formula,
        /// of course, can depend on the values of variables.  The value of a variable is the 
        /// value of the spreadsheet cell it names (if that cell's value is a double) or 
        /// is undefined (otherwise).
        /// 
        /// Spreadsheets are never allowed to contain a combination of Formulas that establish
        /// a circular dependency.  A circular dependency exists when a cell depends on itself.
        /// For example, suppose that A1 contains B1*2, B1 contains C1*2, and C1 contains A1*2.
        /// A1 depends on B1, which depends on C1, which depends on A1.  That's a circular
        /// dependency.
        /// </summary>
        public Spreadsheet()
        {
            dep = new DependencyGraph();
            dic = new Dictionary<string, Cell>();
        }

        /// <summary>
        /// If name is null or invalid, throws an InvalidNameException.
        /// 
        /// Otherwise, returns the contents (as opposed to the value) of the named cell.  The return
        /// value should be either a string, a double, or a Formula.
        /// </summary>
        /// <param name="name">Name of cell</param>
        /// <returns>Contents of Cell</returns>
        public override object GetCellContents(string name)
        {
            // Check for possible exceptions.
            Cell contents;
            if (String.IsNullOrEmpty(name) || !isValidName(name))
                throw new InvalidNameException();
            if (dic.TryGetValue(name, out contents))
                return contents.cellContents;
            return "";
        }

        /// <summary>
        /// Enumerates the names of all the non-empty cells in the spreadsheet.
        /// </summary>
        /// <returns>Set of empty names.</returns>
        public override IEnumerable<string> GetNamesOfAllNonemptyCells()
        {
            // Go through dictionary keys and values, if any key has a value or more return.
            foreach (KeyValuePair<String, Cell> item in dic)
            {
                if (item.Key.Count() > 0)
                    yield return (String) item.Value.cellName;
            }
        }

        /// <summary>
        /// If the formula parameter is null, throws an ArgumentNullException.
        /// 
        /// Otherwise, if name is null or invalid, throws an InvalidNameException.
        /// 
        /// Otherwise, if changing the contents of the named cell to be the formula would cause a 
        /// circular dependency, throws a CircularException.  (No change is made to the spreadsheet.)
        /// 
        /// Otherwise, the contents of the named cell becomes formula.  The method returns a
        /// Set consisting of name plus the names of all other cells whose value depends,
        /// directly or indirectly, on the named cell.
        /// </summary>
        /// <param name="name">Name of cell</param>
        /// <param name="formula">Formula to put into contents</param>
        /// <returns>Dependents</returns>
        public override ISet<string> SetCellContents(string name, Formula formula)
        {
            // Check for possible exceptions.
            Cell contents;
            if (formula == null)
                throw new ArgumentNullException();
            if (String.IsNullOrEmpty(name) || !isValidName(name))
                throw new InvalidNameException();
            // Create cell.
            if (dic.ContainsKey(name))
            {
                dic.TryGetValue(name, out contents);
                contents.cellContents = formula;
            }
            else
            {
                dic.Add(name, new Cell(name, formula));
            }

            // Fix dependency.
            foreach (string item in formula.GetVariables())
            {
                try
                {
                    dep.AddDependency(item, name);
                }
                catch (InvalidOperationException)
                {
                    throw new CircularException();
                }
            }
            // Get set to return.
            HashSet<string> depend = new HashSet<string>(GetCellsToRecalculate(name));
            depend.Add(name);
            return depend;

        }

        /// <summary>
        /// If text is null, throws an ArgumentNullException.
        /// 
        /// Otherwise, if name is null or invalid, throws an InvalidNameException.
        /// 
        /// Otherwise, the contents of the named cell becomes text.  The method returns a
        /// set consisting of name plus the names of all other cells whose value depends, 
        /// directly or indirectly, on the named cell.
        /// </summary>
        /// <param name="name">Name of cell</param>
        /// <param name="text">Text to put into contents</param>
        /// <returns>Dependants</returns>
        public override ISet<string> SetCellContents(string name, string text)
        {
            // Check for possible exceptions.
            Cell contents;
            if (String.IsNullOrEmpty(text))
                throw new ArgumentNullException();
            if (String.IsNullOrEmpty(name) || !isValidName(name))
                throw new InvalidNameException();
            if (dic.ContainsKey(name))
            {
                dic.TryGetValue(name, out contents);
                contents.cellContents = text;
            }
            else
            {
                dic.Add(name, new Cell(name, text));
            }
            // Get set to return.
            HashSet<string> depend = new HashSet<string>(GetCellsToRecalculate(name));
            depend.Add(name);
            return depend;
        }

        /// <summary>
        /// If name is null or invalid, throws an InvalidNameException.
        /// 
        /// Otherwise, the contents of the named cell becomes number.  The method returns a
        /// set consisting of name plus the names of all other cells whose value depends, 
        /// directly or indirectly, on the named cell.
        /// </summary>
        /// <param name="name">Name of cells</param>
        /// <param name="number">Name to put into contents</param>
        /// <returns>Dependants</returns>
        public override ISet<string> SetCellContents(string name, double number)
        {
            // Check for possible exceptions.
            Cell contents;
            if (String.IsNullOrEmpty(name) || !isValidName(name))
                throw new InvalidNameException();
            if (dic.ContainsKey(name))
            {
                dic.TryGetValue(name, out contents);
                contents.cellContents = number;
            }
            else
            {
                dic.Add(name, new Cell(name, number));
            }
            // Get set to return.
            HashSet<string> depend = new HashSet<string>(GetCellsToRecalculate(name));
            depend.Add(name);
            return depend;
        }

        /// <summary>
        /// If name is null, throws an ArgumentNullException.
        /// 
        /// Otherwise, if name isn't a valid cell name, throws an InvalidNameException.
        /// 
        /// Otherwise, returns an enumeration, without duplicates, of the names of all cells whose
        /// values depend directly on the value of the named cell.  In other words, returns
        /// an enumeration, without duplicates, of the names of all cells that contain
        /// formulas containing name.
        /// </summary>
        /// <param name="name">Name of cell</param>
        /// <returns>Dependants</returns>
        protected override IEnumerable<string> GetDirectDependents(string name)
        {
            // Check for possible exceptions.
            if (String.IsNullOrEmpty(name))
                throw new ArgumentNullException();
            if (!isValidName(name))
                throw new InvalidNameException();
            // Get dependents.
            if (dep.HasDependents(name))
                return dep.GetDependents(name);
            return Enumerable.Empty<string>();
        }

        /// <summary>
        /// Check if the name is valid.
        /// </summary>
        /// <param name="name">Name to be checked</param>
        /// <returns>True if valid, false if not.</returns>
        private Boolean isValidName(string name)
        {
            if (Regex.IsMatch(name, "^[a-zA-Z]+[1-9][0-9]*$"))
                return true;
            return false;
        }

        /// <summary>
        /// A cell to represent the contents and name of a cell in the spreadsheet.
        /// </summary>
        private class Cell
        {
            /// <summary>
            /// Set and get the name of the cell.
            /// </summary>
            public string cellName
            {
                get;
                set;
            }

            /// <summary>
            /// Set and get the contents of the cell.
            /// </summary>
            public object cellContents
            {
                get;
                set;
            }

            /// <summary>
            /// Create a cell using a formula.
            /// </summary>
            /// <param name="name">Name of cell</param>
            /// <param name="formula">Contents of cell</param>
            public Cell (string name, Formula formula)
            {
                cellName = name;
                cellContents = formula;
            }
            
            /// <summary>
            /// Create a cell using text.
            /// </summary>
            /// <param name="name">Name of cell</param>
            /// <param name="text">Contents of cell</param>
            public Cell (string name, string text)
            {
                cellName = name;
                cellContents = text;
            }

            /// <summary>
            /// Create a cell using a number.
            /// </summary>
            /// <param name="name">Name of cell</param>
            /// <param name="number">Contents of cell</param>
            public Cell (string name, double number)
            {
                cellName = name;
                cellContents = number;
            }
        }
    }
}
