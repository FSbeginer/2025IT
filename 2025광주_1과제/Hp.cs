using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제
{
    internal class Hp
    {
        public static Model.user user;
        public static Model.company company;

        public static void msgInfo(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "정보", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Information);
        }
        public static void msgErr(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "경고", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Error);
        }

        public static Image GetImage(string path)
        {
            return Image.FromFile("./datafiles/"+path);
        }

        public static string GetInfo(int ino)
        {
            return ino == 0 ? "중소기업" : ino == 1 ? "중견기업" : "대기업";
        }

        public static Region GetRoundRegion(Rectangle rect)
        {
            using (var path = new GraphicsPath())
            {
                path.StartFigure();
                int aw = 40;
                int ah = 40;
                path.AddArc(0,0,aw,ah,180,90);
                path.AddArc(rect.Width-aw,0,aw,ah,270,90);
                path.AddArc(rect.Width-aw,rect.Height-ah,aw,ah,0,90);
                path.AddArc(0,rect.Height-ah,aw,ah,90,90);
                return new Region(path);
            }
        }
    }
}
