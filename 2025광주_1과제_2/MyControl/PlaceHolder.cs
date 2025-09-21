using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제_2.MyControl
{
    internal class PlaceHolder : TextBox
    {
        public string place { get {return lbl.Text; } set {lbl.Text = value; } }
        Label lbl;
        public PlaceHolder()
        {
            lbl = new Label()
            {
                Dock = DockStyle.Fill,
                ForeColor = Color.LightGray,
                TextAlign = ContentAlignment.TopLeft,
                Enabled = false
            };
            Controls.Add(lbl);
            Enter += PlaceHolder_Enter;
            Leave += PlaceHolder_Leave;


        }

        private void PlaceHolder_Leave(object sender, EventArgs e)
        {
            if (Text.Length > 0)
            {
                lbl.Visible = false;
            }
            else
            {
                lbl.Visible = true;
            }
        }

        private void PlaceHolder_Enter(object sender, EventArgs e)
        {
           lbl.Visible = false;
        }
    }
}
