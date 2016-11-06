using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SpreadsheetUtilities;
using SS;
using System.Collections.Generic;

namespace SpreadsheetTests
{
    /// <summary>
    /// Tests for the spreadsheet.
    /// </summary>
    [TestClass]
    public class SpreadsheetTests
    {
        /// <summary>
        /// Test adding and overwriting cells.
        /// </summary>
        [TestMethod]
        public void MassTest1()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.SetCellContents("A1", "Cell1");
            sheet.SetCellContents("B1", "Cell2");
            sheet.SetCellContents("C1", "Cell3");
            sheet.SetCellContents("D1", "Cell4");
            Assert.AreEqual("Cell1", sheet.GetCellContents("A1"));
            Assert.AreEqual("Cell2", sheet.GetCellContents("B1"));
            Assert.AreEqual("Cell3", sheet.GetCellContents("C1"));
            Assert.AreEqual("Cell4", sheet.GetCellContents("D1"));
            sheet.SetCellContents("A1", "ChangedCell1");
            sheet.SetCellContents("B1", "ChangedCell2");
            sheet.SetCellContents("C1", "ChangedCell3");
            sheet.SetCellContents("D1", "ChangedCell4");
            Assert.AreEqual("ChangedCell1", sheet.GetCellContents("A1"));
            Assert.AreEqual("ChangedCell2", sheet.GetCellContents("B1"));
            Assert.AreEqual("ChangedCell3", sheet.GetCellContents("C1"));
            Assert.AreEqual("ChangedCell4", sheet.GetCellContents("D1"));
        }

        /// <summary>
        /// Test resetting.
        /// </summary>
        [TestMethod]
        public void MassTest2()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.SetCellContents("A1", "Cell1");
            sheet.SetCellContents("B1", "Cell1");
            sheet.SetCellContents("C1", "Cell1");
            sheet.SetCellContents("D1", "Cell1");
            Assert.AreNotEqual("NotCell", sheet.GetCellContents("A1"));
            Assert.AreNotEqual("NotCell", sheet.GetCellContents("B1"));
            Assert.AreNotEqual("NotCell", sheet.GetCellContents("C1"));
            Assert.AreNotEqual("NotCell", sheet.GetCellContents("D1"));
            IEnumerable<string> names = sheet.GetNamesOfAllNonemptyCells();
            HashSet<string> correct = new HashSet<string>() { "A1", "B1", "C1", "D1" };
            foreach (String item in names)
                Assert.IsTrue(correct.Contains(item));
        }

        /// <summary>
        /// Tests using formula.
        /// </summary>
        [TestMethod]
        public void MassTest3()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.SetCellContents("A1", new Formula("10/5"));
            sheet.SetCellContents("B1", new Formula("10*(10+5)"));
            sheet.SetCellContents("C1", new Formula("10-2"));
            sheet.SetCellContents("D1", new Formula("10+11"));
            Assert.AreEqual(new Formula("10/5"), sheet.GetCellContents("A1"));
            Assert.AreEqual(new Formula("10*(10+5)"), sheet.GetCellContents("B1"));
            Assert.AreEqual(new Formula("10-2"), sheet.GetCellContents("C1"));
            Assert.AreEqual(new Formula("10+11"), sheet.GetCellContents("D1"));
            IEnumerable<string> names = sheet.GetNamesOfAllNonemptyCells();
            HashSet<string> correct = new HashSet<string>() { "A1", "B1", "C1", "D1" };
            foreach (String item in names)
                Assert.IsTrue(correct.Contains(item));
        }

        /// <summary>
        /// Tests using doubles.
        /// </summary>
        [TestMethod]
        public void MassTest4()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.SetCellContents("A1", 10.0);
            sheet.SetCellContents("B1", 5.0);
            sheet.SetCellContents("C1", 2.0);
            sheet.SetCellContents("D1", 3.313);
            Assert.AreEqual(10.0, sheet.GetCellContents("A1"));
            Assert.AreEqual(5.0, sheet.GetCellContents("B1"));
            Assert.AreEqual(2.0, sheet.GetCellContents("C1"));
            Assert.AreEqual(3.313, sheet.GetCellContents("D1"));
            IEnumerable<string> names = sheet.GetNamesOfAllNonemptyCells();
            HashSet<string> correct = new HashSet<string>() { "A1", "B1", "C1", "D1" };
            foreach (String item in names)
                Assert.IsTrue(correct.Contains(item));
        }

        /// <summary>
        /// Should throw error for improper name.
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(InvalidNameException))]
        public void ErrorTest1()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.GetCellContents("125afad");
        }

        /// <summary>
        /// Should throw error for null name.
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(InvalidNameException))]
        public void ErrorTest2()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.GetCellContents(null);
        }

        /// <summary>
        /// Should throw error for null formula.
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void ErrorTest3()
        {
            Spreadsheet sheet = new Spreadsheet();
            Formula form = null;
            sheet.SetCellContents("A1", form);
        }

        /// <summary>
        ///  Should throw error for null text.
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void ErrorTest4()
        {
            Spreadsheet sheet = new Spreadsheet();
            String text = null;
            sheet.SetCellContents("A1", text);
        }

        /// <summary>
        /// Test from the assignment example.
        /// </summary>
        [TestMethod]
        public void ExampleTest1()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.SetCellContents("B1", new Formula("A1*2"));
            sheet.SetCellContents("C1", new Formula("B1+A1"));
            IEnumerable<string> names = sheet.SetCellContents("A1", new Formula("2+2"));
            HashSet<string> correct = new HashSet<string>() { "A1", "B1", "C1"};
            foreach (String item in names)
                Assert.IsTrue(correct.Contains(item));
        }

        /// <summary>
        /// Test from the assignment example.
        /// </summary>
        [TestMethod]
        public void ExampleTest2()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.SetCellContents("B1", new Formula("A1*2"));
            sheet.SetCellContents("C1", new Formula("B1+A1"));
            IEnumerable<string> names = sheet.SetCellContents("A1", "Boo");
            HashSet<string> correct = new HashSet<string>() { "A1", "B1", "C1" };
            foreach (String item in names)
                Assert.IsTrue(correct.Contains(item));
        }

        /// <summary>
        /// Text from the assignment example.
        /// </summary>
        [TestMethod]
        public void ExampleTest3()
        {
            Spreadsheet sheet = new Spreadsheet();
            sheet.SetCellContents("B1", new Formula("A1*2"));
            sheet.SetCellContents("C1", new Formula("B1+A1"));
            IEnumerable<string> names = sheet.SetCellContents("A1", 10.0);
            HashSet<string> correct = new HashSet<string>() { "A1", "B1", "C1" };
            foreach (String item in names)
                Assert.IsTrue(correct.Contains(item));
        }
    }
}
