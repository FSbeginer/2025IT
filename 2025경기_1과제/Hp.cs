using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _2025경기_1과제
{
    internal class Hp
    {
        public static users user;
        public static movie selMovie;
        public static DateTime selDate;
        public static TimeSpan selTime;

        public static void msgInfo(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg,"정보", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Information);
        }
        public static void msgErr(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg,"경고", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Error);
        }
        public static Image GetImage(string url)
        {
            return Image.FromFile("./datafiles/"+url);
        }
        public static Bitmap GetGrayImage(Image img)
        {
            Bitmap bit = new Bitmap(img);
            for (int i = 0; i < bit.Width; i++)
            {
                for (global::System.Int32 j = 0; j < bit.Height; j++)
                {
                    Color c = bit.GetPixel(i, j); 
                    int m = (c.R + c.G + c.B)/3;
                    bit.SetPixel(i, j, Color.FromArgb(m,m,m));
                }
            }
            return bit;
        }
        public static int GetAge(DateTime birth)
        {
            int age = DateTime.Now.Year - birth.Year;
            if (birth.AddYears(age) > DateTime.Now.Date)
            {
                age--;
            }
            return age;
        }
    }
}
