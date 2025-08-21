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

namespace _2025부산_1과제.Controls
{
    public partial class CircleButton : UserControl
    {
        public CircleButton()
        {
            InitializeComponent();
            using (GraphicsPath path = new GraphicsPath())
            {
                path.StartFigure();
                path.AddEllipse(0,0,25,25);
                Region = new Region(path);
            }
            using (GraphicsPath path = new GraphicsPath())
            {
                path.StartFigure();
                path.AddEllipse(1,1,23,23);
                panel1.Region = new Region(path);
            }
            
        }
    }
}
