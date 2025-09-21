using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class G_마이페이지 : _2025광주_1과제_2.Template.BF
    {
        public G_마이페이지()
        {
            InitializeComponent();
        }

        private void G_마이페이지_Load(object sender, EventArgs e)
        {
            panel2.Controls.Add(new MyControl.마이페이지());
        }

        private void label1_Click(object sender, EventArgs e)
        {
            panel2.Controls.Clear(); 
            panel2.Controls.Add(new MyControl.마이페이지());
        }

        private void label2_Click(object sender, EventArgs e)
        {
            panel2.Controls.Clear();
            panel2.Controls.Add(new MyControl.관심있는회사공고());
        }

        private void label3_Click(object sender, EventArgs e)
        {
            panel2.Controls.Clear();
            panel2.Controls.Add(new MyControl.관심있는소개서());
        }
    }
}
