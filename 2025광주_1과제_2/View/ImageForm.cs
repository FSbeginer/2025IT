using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class ImageForm : _2025광주_1과제_2.Template.BF
    {
        public ImageForm(int w, int h, Image img)
        {
            InitializeComponent();
            ClientSize = new Size(w, h);
            pictureBox1.Image = img;
        }

        private void ImageForm_Load(object sender, EventArgs e)
        {

        }
    }
}
