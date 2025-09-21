using _2025광주_1과제_2.Model;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제_2.MyControl
{
    internal class Hp
    {
        public static user user;
        public static company company;
        public static void msgInfo(string msg)
        {
            MessageBox.Show(msg, "정보", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
        public static void msgErr(string msg)
        {
            MessageBox.Show(msg, "경고", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
        public static Image GetImage(string path)
        {
            return Image.FromFile("./datafiles/" + path);
        }
        public static string companySize(int idx)
        {
            return idx == 0 ? "중소기업" : idx == 1 ? "중견기업" : "대기업";

        }
        public static Region GetRegion(Rectangle rectangle)
        {
            using (var path = new GraphicsPath())
            {
                path.StartFigure();
                path.AddArc(0,0,20,20,180,90);
                path.AddArc(rectangle.Width-20,0,20,20,270,90);
                path.AddArc(rectangle.Width-20,rectangle.Height-20,20,20,0,90);
                path.AddArc(0,rectangle.Height-20,20,20,90,90);
                return new Region(path);
            }
        }

    }
}
