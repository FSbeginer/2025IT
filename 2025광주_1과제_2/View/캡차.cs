using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제_2.View
{
    public partial class 캡차 : Form
    {
        public 캡차(Image img)
        {
            InitializeComponent();
            pictureBox1.Image = img;    
        }

        private void 캡차_Load(object sender, EventArgs e)
        {

        }
        Point p1, p2;
        private Point[] ps;

        private void pictureBox1_MouseDown(object sender, MouseEventArgs e)
        {
            ps = null;
            p1 = e.Location;
        }

        private void pictureBox1_Paint(object sender, PaintEventArgs e)
        {
            if(ps != null)
            {
                using (var path = new GraphicsPath())
                {
                    path.AddPolygon(ps);
                    e.Graphics.DrawPath(Pens.Red,path);
                }
            }
        }

        private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
        {
            p2 = e.Location;

            ps =new[] {p1, new Point(p1.X,p2.Y),p2, new Point(p2.X, p1.Y) };
            int w = Math.Abs(p1.X - p2.X);
            int h = Math.Abs(p1.Y - p2.Y);

            if (w == 0 || h == 0) return;
            Bitmap bitmap = new Bitmap(w,h);
            using (var g = Graphics.FromImage(bitmap))
            {
                int minX = Math.Min(p1.X, p2.X);
                int minY = Math.Min(p1.Y, p2.Y);

                Rectangle src = new Rectangle(minX,minY,w,h);
                Rectangle dest = new Rectangle(0,0,w,h);
                g.DrawImage(pictureBox1.Image, dest, src, GraphicsUnit.Pixel);
            }

            Refresh();

            new ImageForm(w, h, bitmap).ShowDialog();
        }
    }
}
