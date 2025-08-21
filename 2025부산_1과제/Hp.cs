using _2025부산_1과제.Model;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _2025부산_1과제
{
    internal class Hp
    {
        public static User user;
        public static bool isAdmin;
        public static bool isSpecial;
        public static Division division;
        public static Station start,end;
        public static TimeSpan selTime;
        public static TimeSpan selSTime;
        public static DateTime selDate;
        public static string seat;
        public static int carNo;
        public static int price;

        public static void reset()
        {
            isSpecial = false;
            division = default;
            start = default; end = default;
            selTime = default;
            selDate = default;
            selSTime = default;
            seat = default;
            carNo = default; price = default;
        }
        public static void msgInfo(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "정보", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Information);
        }
        public static void msgErr(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "정보", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Error);
        }
        public static Image GetImage(string path)
        {
            return Image.FromFile("./datafiles/"+path);
        }
        public static int Getage(DateTime birth)
        {
            int age = DateTime.Now.Date.Year - birth.Year;
            if (birth.AddYears(age) > DateTime.Now.Date)
                age--;
            return age;
        }
        public static string getReservationString(Reservation re, string scode)
        {
            string result=default;
            result = re.r_date.Value.ToString("yyyyMMdd") + "-" + scode+ "-" + re.r_car.Value.ToString("D2") + (re.r_seat[0] - 64) + re.r_seat.Substring(1);
            return result;
        }
    }
}
