using _2025부산_1과제.Model;
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
    public partial class Map : UserControl
    {
        public List<(Station, Station)> connection = new List<(Station, Station)>();
        public Color[] c = { Color.Red, Color.FromArgb(0, 100, 200), Color.FromArgb(150, 200, 230), Color.FromArgb(170, 200, 150), Color.Lime, Color.Orange, Color.FromArgb(190, 190, 190) };
        private Division _division;
        public bool showText { get; set; }
        public int[,] dist;
        public int[,] prev;


        public Division Division
        {
            get { return _division; }
            set { _division = value; getConnection(); }
        }

        private void getConnection()
        {
            connection.Clear();
            using (var db = new Model.ITTRAINEntities())
            {
                var list = db.Station.AsEnumerable().Where(x => getCode(x.s_code, 0) == Division.d_no).OrderBy(x => x.s_code.Value).ToList();
                var zeroList = list.Where(x => getCode(x.s_code, 2) == 00).ToList();
                for (global::System.Int32 i = 0; i < zeroList.Count - 1; i++)
                {
                    var now = zeroList[i];
                    var next = zeroList[i + 1];
                    var nextList = list.Where(x => x.s_code.Value > now.s_code.Value && x.s_code.Value <= next.s_code.Value).GroupBy(x => getCode(x.s_code, 2) / 10).ToList();
                    foreach (var item in nextList)
                    {
                        var group = item.ToList();
                        connection.Add((now, group[0]));
                        for (global::System.Int32 j = 0; j < group.Count - 1; j++)
                        {
                            connection.Add((group[j], group[j + 1]));
                        }
                        connection.Add((group.Last(), next));
                    }
                }

                int n = db.Information.Count();
                dist = new int[n, n];
                prev = new int[n, n];
                for (global::System.Int32 i = 0; i < n; i++)
                {
                    for (global::System.Int32 j = 0; j < n; j++)
                    {
                        prev[i, j] = -1;
                        dist[i, j] = i == j ? 0 : 999999;
                    }
                }
                foreach (var item in connection)
                {
                    var (start, end) = item;
                    int x1 = start.Information.x.Value;
                    int y1 = start.Information.y.Value;
                    int x2 = end.Information.x.Value;
                    int y2 = end.Information.y.Value;
                    int dx = x1 - x2;
                    int dy = y1 - y2;
                    int idx1 = start.i_no.Value - 1, idx2 = end.i_no.Value - 1;
                    if (idx1 == idx2) continue;
                    dist[idx1, idx2] = (int)Math.Round(Math.Sqrt(dx * dx + dy * dy));
                    dist[idx2, idx1] = (int)Math.Round(Math.Sqrt(dx * dx + dy * dy));
                    prev[idx1, idx2] = idx1;
                    prev[idx2, idx1] = idx2;
                }
                for (global::System.Int32 v = 0; v < n; v++)
                {
                    for (global::System.Int32 i = 0; i < n; i++)
                    {
                        for (global::System.Int32 j = 0; j < n; j++)
                        {
                            int newD = dist[i, v] + dist[v, j];
                            if (newD < dist[i, j])
                            {
                                dist[i, j] = newD;
                                prev[i, j] = prev[v, j];
                            }
                        }
                    }
                }
            }
        }

        public List<int> GetRoute(Station start, Station end)
        {
            List<int> route = new List<int>();
            route.Add(end.i_no.Value);
            int p = prev[start.i_no.Value-1, end.i_no.Value-1];
            while (p != -1)
            {
                route.Add(p+1);
                Console.WriteLine(p);
                p = prev[start.i_no.Value-1, p];
            }
            route.Reverse();
            return route;
        }

        public Map()
        {
            InitializeComponent();
        }

        private void Map_Load(object sender, EventArgs e)
        {

        }

        public int getCode(int? scode, int n)
        {
            if (n == 0)
                return scode.Value / 10000;
            else if (n == 1)
                return scode.Value / 100 % 100;
            else
                return scode.Value % 100;
        }
        private void Map_Paint(object sender, PaintEventArgs e)
        {
            if (showText)
            {
                var g = e.Graphics;
                using (var brush = new SolidBrush(c[Division.d_no - 1]))
                using (var matrix = new Matrix())
                {
                    g.FillPolygon(brush, new Point[] { new Point(0, 0), new Point(0, 100), new Point(100, 0) });
                    matrix.Translate(25, 25);
                    matrix.Rotate(-45);
                    var font = new Font("맑은 고딕", 15);
                    g.Transform = matrix;
                    var size = g.MeasureString(Division.d_name, font).Width;
                    if (showText)
                        g.DrawString(Division.d_name, font, Brushes.White, -size / 2, 0);
                    g.ResetTransform();
                }
            }
        }
    }
}
