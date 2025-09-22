using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace _2025광주_1과제.View
{
    public partial class Form캡차 : _2025광주_1과제.Model.BF
    {
        Point p1, p2;
        public Form캡차(Model.company com)
        {
            InitializeComponent();
            pictureBox1.Image = Hp.GetImage("company/" + com.c_no+".png");
        }

        private void pictureBox1_MouseDown(object sender, MouseEventArgs e)
        {
            p1 = e.Location;
        }

        private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
        {
            p2 = e.Location;
            pictureBox1.Invalidate();
        }

        private void pictureBox1_Paint(object sender, PaintEventArgs e)
        {
            if(p1 == default || p2 == default) return;

            int w = Math.Abs(p1.X-p2.X);
            int h = Math.Abs(p1.Y - p2.Y);
            int x = Math.Min(p1.X, p2.X);
            int y = Math.Min(p1.Y, p2.Y);

            if(w == 0 || h == 0) return;

            var srcRect = new Rectangle(x, y, w, h);
            var dstRect = new Rectangle(0, 0, w,h);

            var bitmap = new Bitmap(w, h);
            using (var g = Graphics.FromImage(bitmap))
            {
                g.DrawImage(pictureBox1.Image, dstRect, srcRect, GraphicsUnit.Pixel);
            }
            e.Graphics.DrawPolygon(Pens.Red, new[]
            {
                p1, new Point(p2.X, p1.Y), p2, new Point(p1.X, p2.Y)
            });

            new ImageForm(bitmap).Show();
        }

        private void Form캡차_Load(object sender, EventArgs e)
        {
            
        }
    }
}
