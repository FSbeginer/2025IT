using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class 별점 : _2025경기_1과제.Template.BF
    {
        bool drag;
        Point p = new Point();
        public 별점()
        {
            InitializeComponent();
        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            using (var path = new GraphicsPath())
            {
                path.StartFigure();
                path.AddString("★★★★★", panel1.Font.FontFamily, (int)panel1.Font.Style, 40, new Point(0, 0), StringFormat.GenericDefault);
                g.DrawPath(Pens.Yellow, path);
                Region region = new Region(path);
                int w = (int)(path.GetBounds().Width / 5) + 3;//때려맞추기
                Region region2 = new Region(new Rectangle(0, 0, w * (p.X / w), panel1.Height));
                region.Intersect(region2);

                g.FillRegion(Brushes.Yellow, region);
                label1.Text = (p.X / w) + "점";
            }
        }


        private void panel1_MouseDown(object sender, MouseEventArgs e)
        {
            drag = true;
        }

        private void panel1_MouseUp(object sender, MouseEventArgs e)
        {
            drag = false;
        }
        private void panel1_MouseMove(object sender, MouseEventArgs e)
        {
            if (drag)
            {
                p = e.Location;
                panel1.Invalidate();
            }
        }
    }
}
