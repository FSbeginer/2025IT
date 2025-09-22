using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class ImageForm : _2025광주_1과제.Model.BF
    {
        public ImageForm(Bitmap bit)
        {
            InitializeComponent();
            Size = bit.Size;
            pictureBox1.Image = bit;
        }

        private void ImageForm_Load(object sender, EventArgs e)
        {

        }
    }
}
