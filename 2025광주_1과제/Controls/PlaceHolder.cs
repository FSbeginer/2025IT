using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제.Controls
{
    public partial class PlaceHolder : UserControl
    {
        public Label lbl;

        public string Msg
        {
            get { return lbl.Text; }
            set { lbl.Text = value; }
        }

        public PlaceHolder()
        {
            InitializeComponent();
            Size = textBox1.Size;
            lbl = new Label()
            {
                Dock = DockStyle.Fill,
                ForeColor = Color.LightGray,
                TextAlign = ContentAlignment.TopLeft,
                Enabled = false
            };
            textBox1.Controls.Add(lbl);
        }

        private void textBox1_Enter(object sender, EventArgs e)
        {
            lbl.Visible = false;
        }

        private void textBox1_Leave(object sender, EventArgs e)
        {
            if (textBox1.Text.Length > 0)
            {
                lbl.Visible = false;
            }
            else
            {
                lbl.Visible = true;
            }

        }
    }
}
