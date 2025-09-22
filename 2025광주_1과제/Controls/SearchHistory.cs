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
    public partial class SearchHistory : UserControl
    {
        public SearchHistory()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {
            (FindForm() as Form메인).placeHolder1.textBox1.Text = label1.Text;
        }

        private void label2_Click(object sender, EventArgs e)
        {
            
        }
    }
}
