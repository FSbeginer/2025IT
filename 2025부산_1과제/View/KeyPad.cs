using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class KeyPad : _2025부산_1과제.Template.BF
    {
        public TextBox textbox { get; set; }
        public KeyPad()
        {
            InitializeComponent();
        }

        private void KeyPad_Load(object sender, EventArgs e)
        {
            setKey();
        }

        private void setKey()
        {
            tableLayoutPanel1.Controls.Clear();
            Random random = new Random();
            var list = Enumerable.Range(0, 12).OrderBy(x=>random.Next()).Select(n => new Label
            {
                Text = n>=10? "" : n + "",
                TextAlign = ContentAlignment.MiddleCenter,
                BorderStyle = BorderStyle.FixedSingle,
                BackColor = n>=10? Color.Gray : Color.White,
                Margin = new Padding(0),
                Dock = DockStyle.Fill,
            }).ToList();
            
            foreach (var item in list)
            {
                item.Click += Item_Click;
                tableLayoutPanel1.Controls.Add(item);
            }
        }

        private void Item_Click(object sender, EventArgs e)
        {
            Label lbl = sender as Label;
            textbox.Text = textbox.Text + lbl.Text;
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            setKey();
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            textbox.Text = textbox.Text.Substring(0,textbox.TextLength-1);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            textbox.Clear();
        }
    }
}
