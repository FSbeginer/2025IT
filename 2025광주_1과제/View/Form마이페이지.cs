using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form마이페이지 : _2025광주_1과제.Model.BF
    {
        public Form마이페이지()
        {
            InitializeComponent();
        }

        private void Form마이페이지_Load(object sender, EventArgs e)
        {
            panel2.Controls.Add(new Controls.Control마이페이지());
        }

        private void label1_Click(object sender, EventArgs e)
        {
            panel2.Controls.Clear();
            panel2.Controls.Add(new Controls.Control마이페이지());
        }

        private void label2_Click(object sender, EventArgs e)
        {
            panel2.Controls.Clear();
            panel2.Controls.Add(new Controls.Control관심있회사());
        }

        private void label3_Click(object sender, EventArgs e)
        {
            panel2.Controls.Clear();
            panel2.Controls.Add(new Controls.Control관심있자소서());
        }
    }
}
